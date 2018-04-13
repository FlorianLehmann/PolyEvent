import os
import subprocess
import sys

polyevent_backend = 'polyeventbackend'
polyevent_backend_war = 'polyeventbackendwar'


def run_cmd(cmd):
    with subprocess.Popen(cmd, shell=True, stdout=sys.stdout) as p:
        stdout, stderr = p.communicate()
        print(stdout)


def mvn_clean_install():
    return ['mvn', 'clean', 'install']


def docker_build():
    return ['docker', 'build', '.', '--tag="polyevent"']


def docker_run():
    return ['docker', 'run', '-p', '8080:8080', 'polyevent']


def main():
    os.chdir(polyevent_backend)
    run_cmd(mvn_clean_install())
    os.chdir(polyevent_backend_war)
    run_cmd(docker_build())
    run_cmd(docker_run())


if __name__ == '__main__':
    main()
